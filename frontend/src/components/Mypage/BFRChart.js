import { React, useState, useEffect } from "react";
import axios from "axios";
//import ReactApexChart from "react-apexcharts";
import BubbleChart from "@testboxlab/react-bubble-chart-d3";

const BFRChart = () => {
  const userId = sessionStorage.getItem("userId");
  const [bfr, setBFR] = useState("");

  useEffect(() => {
    if (userId) {
      axios
        .get("/bfr/result/" + sessionStorage.getItem("userId"))
        .then((response) => {
          setBFR(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      // userId가 없으면 메인 페이지로 이동
      window.location.href = "/";
    }
  }, []);
  //"소비", "예적금", "투자(주식, 코인)", "고정비용(보험,세금)"
  console.log("BFR", bfr);
  console.log("consumption type", typeof bfr.consumption);
  var consumption = bfr.consumption;

  return (
    <BubbleChart
      width={450}
      height={450}
      fontFamily="Arial"
      data={[
        {
          label: `소비`,
          value: 11,
        },
        { label: "예적금", value: 22 },
        { label: "투자(주식, 코인)", value: 33 },
        { label: "고정비용(보험,세금)", value: 44 },
      ]}
      showLegend={true}
      graph={{
        zoom: 1,
        offsetX: 2,
        offsety: 2,
      }}
      charsBeforeSplit={12}
    />
  );
};

export default BFRChart;
