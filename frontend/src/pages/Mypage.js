import { React, useState, useEffect } from "react";
import { Button, Card, Container, Row, Col } from "reactstrap";
import DemoNavbar from "components/Navbars/DemoNavbar.js";
import SimpleFooter from "components/Footers/SimpleFooter.js";
import BFRChart from "components/Mypage/BFRChart.js";
import axios from "axios";

const Profile = () => {
  const userId = sessionStorage.getItem("userId");

  // 기존 Argon
  const [isMounted, setIsMounted] = useState(false);

  useEffect(() => {
    if (isMounted) {
      document.documentElement.scrollTop = 0;
      document.scrollingElement.scrollTop = 0;
    }
    setIsMounted(true);
  }, []);

  // 추가
  const [survey, setSurvey] = useState("");
  const [bfr, setBFR] = useState("");

  // SurveyController
  useEffect(() => {
    if (userId) {
      axios
      .get("/survey/" + sessionStorage.getItem("userId"))
      .then((response) => {
        setSurvey(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
    } else {
      // userId가 없으면 메인 페이지로 이동
      window.location.href = "/";
    }
  }, []);

  console.log("SURVEY", survey);

  if (sessionStorage.getItem("userId") === null) {
    window.location.href = "/";
    return;
  }

  return (
    <>
      <DemoNavbar />
      <main className="profile-page">
        <section className="section-profile-cover section-shaped my-0">
          {/* Circles background */}
          <div className="shape shape-style-1 shape-default alpha-4">
            <span />
            <span />
            <span />
            <span />
            <span />
            <span />
            <span />
          </div>
          {/* SVG separator */}
          <div className="separator separator-bottom separator-skew">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              preserveAspectRatio="none"
              version="1.1"
              viewBox="0 0 2560 100"
              x="0"
              y="0"
            >
              <polygon className="fill-white" points="2560 0 2560 100 0 100" />
            </svg>
          </div>
        </section>
        <section className="section">
          <Container>
            <Card className="card-profile shadow mt--300">
              <div className="px-4">
                <Row className="justify-content-center">
                  <Col className="order-lg-2" lg="3">
                    <div className="card-profile-image">
                      <a href="#pablo" onClick={(e) => e.preventDefault()}>
                        <img
                          alt="..."
                          className="rounded-circle"
                          src={sessionStorage.getItem("profileImage")}
                          maxwidth="250px"
                        />
                      </a>
                    </div>
                  </Col>
                  <Col
                    className="order-lg-3 text-lg-right align-self-lg-center"
                    lg="4"
                  >
                    <div className="card-profile-actions py-4 mt-lg-0">
                      <Button
                        className="mr-4"
                        color="info"
                        href="#pablo"
                        onClick={(e) => e.preventDefault()}
                        size="sm"
                      >
                        Connect
                      </Button>
                      <Button
                        className="float-right"
                        color="default"
                        href="#pablo"
                        onClick={(e) => e.preventDefault()}
                        size="sm"
                      >
                        Message
                      </Button>
                    </div>
                  </Col>
                  <Col className="order-lg-1" lg="4">
                    <div className="card-profile-stats d-flex justify-content-center">
                      <div>
                        <span className="heading">10</span>
                        <span className="description">Friends</span>
                      </div>
                      <div>
                        <span className="heading">10</span>
                        <span className="description">Photos</span>
                      </div>
                      <div>
                        <span className="heading">89</span>
                        <span className="description">Comments</span>
                      </div>
                    </div>
                  </Col>
                </Row>
                <div className="text-center mt-5">
                  <h3>
                    name
                    <span className="font-weight-light">, age</span>
                  </h3>
                  <div className="h6 font-weight-300">
                    <i className="ni location_pin mr-2" />
                    금융대사량
                  </div>
                  <div className="h6 mt-4">
                    <i className="ni business_briefcase-24 mr-2" />
                    Solution Manager - Creative Tim Officer
                  </div>
                  <div>
                    <i className="ni education_hat mr-2" />
                    University of Computer Science
                  </div>
                </div>
                <div className="mt-5 py-5 border-top text-center">
                  <Row className="justify-content-center">
                  <Col lg="4">
                      <div>이름: {sessionStorage.getItem("name")}</div>
                      <div>이메일: {sessionStorage.getItem("email")}</div>
                      <div>성별: {sessionStorage.getItem("gender")}</div>
                      <div>출생년도: {sessionStorage.getItem("birthYear")}</div>
                      <div>연령대: {sessionStorage.getItem("age")}</div>
                      <div>금융대사량 타입: {sessionStorage.getItem("financialType")}</div>
                      <div>목표 갯수: {sessionStorage.getItem("goalCnt")}</div>
                      <div>목표 달성률: {sessionStorage.getItem("achievementRate")}</div>
                      <div>현재까지 모은 금액: {100}</div>
                    </Col>
                    <Col lg="4">
                      <div>
                        결혼 유무: {survey["married"] === 1 ? "기혼" : "미혼"}{" "}
                      </div>
                      <div>월 소득: {survey["monthlyIncome"]}만원</div>
                      <div>직종 : {survey["occupation"]}</div>
                    </Col>
                    <Col lg="4">
                      <div>소비: {bfr["consumption"]}</div>
                      <div>예적금: {bfr["deposit"]}</div>
                      <div>투자(주식, 코인): {bfr["invest"]}</div>
                      <div>고정비용(보험,세금): {bfr["fixedCost"]}</div>
                      <a href="#pablo" onClick={(e) => e.preventDefault()}>
                        Show more
                      </a>
                    </Col>
                  </Row>
                </div>
              </div>
              <BFRChart />
            </Card>
          </Container>
        </section>
      </main>
      <SimpleFooter />
    </>
  );
};

export default Profile;
