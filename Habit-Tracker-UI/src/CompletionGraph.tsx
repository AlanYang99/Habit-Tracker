import React from "react";
import CustomCard from "./CustomCard";
import { Chart, useChart } from "@chakra-ui/charts";
import { Cell, Pie, PieChart, Tooltip } from "recharts";

export default function CompletionGraph() {
  const chart = useChart({
    data: [
      { name: "windows", value: 400, color: "blue.solid" },
      { name: "mac", value: 300, color: "orange.solid" },
      { name: "linux", value: 300, color: "pink.solid" },
      { name: "other", value: 200, color: "green.solid" },
    ],
  });

  return (
    <CustomCard width="30vw">
      <Chart.Root boxSize="200px" mx="auto" chart={chart}>
        <PieChart>
          <Tooltip
            cursor={false}
            animationDuration={100}
            content={<Chart.Tooltip hideLabel />}
          />
          <Pie
            isAnimationActive={false}
            data={chart.data}
            dataKey={chart.key("value")}
            nameKey="name"
            stroke="none"
          >
            {chart.data.map((item) => (
              <Cell key={item.name} fill={chart.color(item.color)} />
            ))}
          </Pie>
        </PieChart>
      </Chart.Root>
    </CustomCard>
  );
}
