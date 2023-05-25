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
                  <Col className="order-lg-2">
                    <div className="card-profile-image">
                      <a href="#pablo" onClick={(e) => e.preventDefault()}>
                        <img
                          alt="..."
                          className="rounded-circle"
                          src={sessionStorage.getItem("profileImage")}
                        />
                      </a>
                    </div>
                  </Col>
                  <Col
                    className="order-lg-3 text-lg-right align-self-lg-center" lg="4">
                    <div className="card-profile-actions py-4 mt-lg-0">
                    </div>
                  </Col>
                  <Col className="order-lg-1" lg="4">
                    <div></div>
                  </Col>
                </Row>
                <div className="text-center mt-5">
                  <h3>
                    {sessionStorage.getItem("name")}
                    <span className="font-weight-light">
                      , {sessionStorage.getItem("age")}
                    </span>
                  </h3>
                  <div className="h3">
                    {sessionStorage.getItem("financialType")}
                  </div>
                </div>
                <div className="card-profile-stats d-flex justify-content-center">
                  <div>
                    <span className="heading">{sessionStorage.getItem("goalCnt")}</span>
                    <span className="description">목표 갯수</span>
                  </div>
                  <div>
                    <span className="heading">{sessionStorage.getItem("achievementRate")}</span>
                    <span className="description">목표 달성률</span>
                  </div>
                  <div>
                    <span className="heading">{sessionStorage.getItem("savingAmount")}</span>
                    <span className="description">현재까지 모은 금액</span>
                  </div>
                </div>
                <div className="mt-5 py-5 border-top text-center">
                  <Row className="justify-content-center">
                    <Col lg="3">
                      <div>
                        결혼 유무: {survey["married"] === 1 ? "기혼" : "미혼"}{" "}
                      </div>
                      <div>월 소득: {survey["monthlyIncome"]}만원</div>
                      <div>직종 : {survey["occupation"]}</div>
                    </Col>
                    <Col lg="9">
                      <BFRChart />
                    </Col>
                  </Row>
                </div>
              </div>
            </Card>
          </Container>
        </section>
      </main>
      <SimpleFooter />
    </>
  );
};

export default Profile;
