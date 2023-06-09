package com.boolsazo.bankchall.controller;

import com.boolsazo.bankchall.domain.Goal;
import com.boolsazo.bankchall.domain.User;
import com.boolsazo.bankchall.dto.GoalListResponse;
import com.boolsazo.bankchall.dto.UserResponse;
import com.boolsazo.bankchall.service.GoalService;
import com.boolsazo.bankchall.service.SavingHistoryService;
import com.boolsazo.bankchall.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@Tag(name = "상태 확인", description = "상태 확인 API")
public class StatusController {

    UserService userService;
    GoalService goalService;
    SavingHistoryService savingHistoryService;

    public StatusController(UserService userService, GoalService goalService,
        SavingHistoryService savingHistoryService) {
        this.userService = userService;
        this.goalService = goalService;
        this.savingHistoryService = savingHistoryService;
    }

    @GetMapping(value = "/login")
    @Operation(summary = "로그인 여부 확인 API", description = "해당 사용자가 로그인된 사용자인지 확인할 수 있는 API")
    public Map<String, Boolean> isLogin(HttpServletRequest request) {
        Map<String, Boolean> result = new HashMap<>();

        if (request.getSession().getAttribute("sessionId") != null) {
            result.put("login", true);
        } else {
            result.put("login", false);
        }

        return result;
    }

    @GetMapping(value = "/bfr")
    @Operation(summary = "금융대사량 검사 여부 확인 API", description = "해당 사용자가 로그인된 사용자인지 확인할 수 있는 API")
    public Map<String, Boolean> isBFR(HttpServletRequest request) {
        Map<String, Boolean> result = new HashMap<>();
        int userId = (int) request.getSession().getAttribute("userId");

        try {
            String bfr = userService.findFinancialTypeByUserId(userId);
            if (bfr != null) {
                result.put("bfr", true);
            } else {
                result.put("bfr", false);
            }
        } catch (Exception e) {
            System.out.println("Error in check financial type");
        }

        return result;
    }

    @GetMapping("/user")
    @Operation(summary = "사용자 정보 확인 API", description = "로그인된 사용자의 정보를 확인할 수 있는 API")
    public UserResponse getUserInfo(HttpSession request) throws Exception {
        UserResponse userResponse = new UserResponse();

        User user = null;
        GoalListResponse goal = null;
        int userId = 0;

        try {
            userId = (int) request.getAttribute("userId");
            user = userService.findByUserId(userId);

            userResponse.setUserId(user.getUserId());
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setGender(user.getGender());
            userResponse.setBirthYear(user.getBirthYear());
            userResponse.setAge(user.getAge());
            userResponse.setProfileImage(user.getProfileImage());
            userResponse.setFinancialType(user.getFinancialType());
        } catch (Exception e) {
            System.out.println("INVALID ACCESS: User Information");
        }

        try {
            goal = goalService.showAllGoal(userId);

            int achievementCount = 0;
            for (Goal data : goal.getGoals()){
                if (!data.isExpired()) {
                    continue;
                }
                ++achievementCount;
            }
            System.out.println("달성한 개수 : "+achievementCount);
            userResponse.setGoalCnt(goal.getCount());
            int achievementRate = goal.getCount() == 0 ? 0 : (int)(Math.round(achievementCount / (double)goal.getCount() * 100));
            userResponse.setAchievementRate(achievementRate);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("THIS USER DOESN'T HAVE ANY GOAL");
        }

        try {
            int savingAmount = savingHistoryService.showSavingAmountByUserId(userId);
            userResponse.setSavingAmount(savingAmount);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("THIS USER DOESN'T SAVE MONEY");
        }

        return userResponse;
    }
}
