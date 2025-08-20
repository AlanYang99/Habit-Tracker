"use client";

import { Chart, useChart } from "@chakra-ui/charts";
import {
  CartesianGrid,
  Legend,
  Line,
  LineChart,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

import CustomCard from "./CustomCard";

export default function LineGraph() {
  const chart = useChart({
    data: [
      { windows: 20, mac: 10, linux: 5, month: "January" },
      { windows: 20, mac: 10, linux: 5, month: "February" },
      { windows: 20, mac: 10, linux: 5, month: "March" },
      { windows: 20, mac: 10, linux: 5, month: "May" },
      { windows: 20, mac: 10, linux: 5, month: "June" },
      { windows: 20, mac: 10, linux: 5, month: "August" },
      { windows: 20, mac: 10, linux: 5, month: "October" },
      { windows: 20, mac: 10, linux: 5, month: "November" },
    ],
    series: [
      { name: "windows", color: "teal.solid" },
      { name: "mac", color: "purple.solid" },
      { name: "linux", color: "blue.solid" },
    ],
  });

  return (
    <CustomCard>
      <Chart.Root maxH="40%" minH="40%" chart={chart}>
        <LineChart data={chart.data}>
          <CartesianGrid stroke={chart.color("border")} vertical={false} />
          <XAxis
            axisLine={false}
            dataKey={chart.key("month")}
            tickFormatter={(value) => value.slice(0, 3)}
            stroke={chart.color("border")}
          />
          <YAxis
            axisLine={false}
            tickLine={false}
            tickMargin={10}
            dataKey={chart.key("windows")}
            stroke={chart.color("border")}
          />
          <Tooltip
            animationDuration={100}
            cursor={{ stroke: chart.color("border") }}
            content={<Chart.Tooltip />}
          />
          <Legend
            verticalAlign="top"
            align="right"
            content={<Chart.Legend />}
          />
          {chart.series.map((item) => (
            <Line
              key={item.name}
              isAnimationActive={false}
              dataKey={chart.key(item.name)}
              strokeWidth={2}
              stroke={chart.color(item.color)}
              dot={false}
              activeDot={false}
            />
          ))}
        </LineChart>
      </Chart.Root>
    </CustomCard>
  );
}
