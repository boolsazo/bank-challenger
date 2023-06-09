package com.boolsazo.bankchall.dto;

import com.boolsazo.bankchall.domain.Occupation;
import com.boolsazo.bankchall.dto.resultSet.OccupationResultSet;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "금융대사량에 따른 직종 통계 응답 DTO")
public class StatisticsJobResponse {

    @Schema(description = "가장 많은 직종", defaultValue = "")
    private List<String> bestJob = new ArrayList<>();
    @Schema(description = "무직", defaultValue = "0")
    private int inoccupation = 0;

    @Schema(description = "학생", defaultValue = "0")
    private int student = 0;

    @Schema(description = "회사원", defaultValue = "0")
    private int employee = 0;

    @Schema(description = "자영업자", defaultValue = "0")
    private int ownerOperator = 0;

    @Schema(description = "전문직", defaultValue = "0")
    private int specializedJob = 0;

    @Schema(description = "프리랜서", defaultValue = "0")
    private int freelancer = 0;

    @Schema(description = "공무원", defaultValue = "0")
    private int civilServant = 0;

    @Schema(description = "엔지니어", defaultValue = "0")
    private int engineer = 0;

    @Schema(description = "서비스직", defaultValue = "0")
    private int service = 0;

    public StatisticsJobResponse(List<OccupationResultSet> result) {
        if (result != null) {
            Optional<OccupationResultSet> occupationResultSetOptional = result.stream().max(
                Comparator.comparing(OccupationResultSet::getCount));
            if (occupationResultSetOptional.isPresent()) {
                int maxCount = occupationResultSetOptional.get().getCount();
                for (OccupationResultSet ors : result) {
                    String occupation = ors.getOccupation();
                    int count = ors.getCount();
                    if (maxCount == count) {
                        bestJob.add(occupation);
                    }
                    if (occupation.equals(Occupation.IN_OCCUPATION.getKor())) {
                        this.inoccupation = count;
                    } else if (occupation.equals(Occupation.STUDENT.getKor())) {
                        this.student = count;
                    } else if (occupation.equals(Occupation.EMPLOYEE.getKor())) {
                        this.employee = count;
                    } else if (occupation.equals(Occupation.OWNER_OPERATOR.getKor())) {
                        this.ownerOperator = count;
                    } else if (occupation.equals(Occupation.SPECIALIZED_JOB.getKor())) {
                        this.specializedJob = count;
                    } else if (occupation.equals(Occupation.FREELANCER.getKor())) {
                        this.freelancer = count;
                    } else if (occupation.equals(Occupation.CIVIL_SERVANT.getKor())) {
                        this.civilServant = count;
                    } else if (occupation.equals(Occupation.SERVICE.getKor())) {
                        this.service = count;
                    } else if (occupation.equals(Occupation.ENGINEERING.getKor())) {
                        this.engineer = count;
                    }
                }
            }
        }
    }
}
