import React from "react";
import {Button, Container, Row, Col} from "reactstrap";

class Hero extends React.Component {
  render() {
    return (
        <>
          <div className="position-relative">
            {/* Hero for FREE version */}
            <section className="section section-hero section-shaped" style={{
              backgroundImage: `url(${require(
                  "../../assets/img/theme/main01.png")})`,
              backgroundSize: "cover",
              backgroundPosition: "center",
              width: '1900px',
              height: '1000px'
            }}>
              {/* Background circles */}
              <div className="shape shape-style-1 shape-default">
                <span className="span-150"/>
                <span className="span-50"/>
                <span className="span-50"/>
                <span className="span-75"/>
                <span className="span-100"/>
                <span className="span-75"/>
                <span className="span-50"/>
                <span className="span-100"/>
                <span className="span-50"/>
                <span className="span-100"/>
              </div>
              <Container
                  className="shape-container d-flex align-items-center py-lg">
                <div className="col px-0">
                  <Row className="align-items-center justify-content-center">
                    <Col className="text-center" lg="6">
                      <div style={{width: '200px'}}></div>
                      <p className="lead text-white"></p>
                      <div className="btn-wrapper mt-5">
                        {" "}
                      </div>
                      뱅크 챌린저는 설정한 목표를 달성할 수 있도록
                      <br/>
                      자동이체를 통해 관리해주는 서비스입니다.
                    </Col>
                  </Row>
                </div>
              </Container>
              {/* SVG separator */}
              {/* <div className="separator separator-bottom separator-skew zindex-100">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                preserveAspectRatio="none"
                version="1.1"
                viewBox="0 0 2560 100"
                x="0"
                y="0"
              >
                <polygon
                  className="fill-white"
                  points="2560 0 2560 100 0 100"
                />
              </svg>
            </div> */}
            </section>
          </div>
        </>
    );
  }
}

export default Hero;
