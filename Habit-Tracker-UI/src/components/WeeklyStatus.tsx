import { Chart, useChart } from "@chakra-ui/charts";
import { Box, Card } from "@chakra-ui/react";
import { Bar, BarChart, CartesianGrid, Tooltip, XAxis, YAxis } from "recharts";

export default function WeeklyStatus() {
  const chart = useChart({
    data: [
      { Completed: 186, InComplete: 80, month: "Monday" },
      { Completed: 165, InComplete: 95, month: "Tuesday" },
      { Completed: 190, InComplete: 87, month: "Wednesday" },
      { Completed: 195, InComplete: 88, month: "Thursday" },
      { Completed: 182, InComplete: 98, month: "Friday" },
      { Completed: 195, InComplete: 88, month: "Saturday" },
      { Completed: 182, InComplete: 98, month: "Sunday" },
    ],
    series: [
      { name: "Completed", color: "teal.solid", stackId: "a" },
      { name: "InComplete", color: "purple.solid", stackId: "a" },
    ],
  });

  return (
    <Card.Root
      background="white/60"
      rounded="4xl"
      ml="20px"
      mr="20px"
      border="2px"
      h="290px"
      w="100%"
      maxW={{ base: "600px", lg: "600px", "2xl": "450px" }}
      display="flex"
      flexDirection="column"
      justifyContent="flex-end"
      alignItems="center"
    >
      <Card.Header>
        <Card.Title>Weekly habit completion status</Card.Title>
      </Card.Header>
      <Card.Body>
        <Chart.Root
          h="200px"
          width={{
            base: "400px",
            lg: "480px",
            "2xl": "400px",
          }}
          chart={chart}
          mr={3}
        >
          <BarChart
            stackOffset="none"
            data={chart.data}
            maxBarSize={15}
            barCategoryGap="0%"
            margin={{ top: 10, right: 20, left: 0, bottom: 5 }}
          >
            <CartesianGrid
              stroke={chart.color("border.muted")}
              vertical={false}
            />
            <XAxis
              axisLine={false}
              tickLine={false}
              dataKey={chart.key("month")}
              tickFormatter={(value) => value.slice(0, 3)}
            />
            <YAxis
              stroke={chart.color("border.emphasized")}
              domain={[0, "dataMax"]}
              tickFormatter={(v) => v.toString()}
            />
            <Tooltip
              cursor={{ fill: chart.color("bg.muted") }}
              animationDuration={50}
              content={<Chart.Tooltip />}
            />

            {chart.series.map((item, idx) => {
              // first series = bottom stack, last series = top stack
              const isFirst = idx === 0;
              const isLast = idx === chart.series.length - 1;

              // [topLeft, topRight, bottomRight, bottomLeft]
              const radius: [number, number, number, number] = isFirst
                ? [0, 0, 10, 10] // round bottom corners
                : isLast
                ? [10, 10, 0, 0] // round top corners
                : [0, 0, 0, 0]; // no rounding for any middle stacks

              return (
                <Bar
                  key={item.name}
                  dataKey={chart.key(item.name)}
                  fill={chart.color(item.color)}
                  stroke={chart.color(item.color)}
                  stackId={item.stackId}
                  isAnimationActive={false}
                  radius={radius}
                />
              );
            })}
          </BarChart>
        </Chart.Root>
      </Card.Body>
    </Card.Root>
  );
}
