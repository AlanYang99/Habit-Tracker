import { ProgressCircle, Box, AbsoluteCenter } from "@chakra-ui/react";
import CustomCard from "./CustomCard";

export default function CompletionCircle() {
  return (
    <Box w="100%" h="100%" pr={4} pb={4}>
      <CustomCard>
        <ProgressCircle.Root value={30} colorPalette="green">
          <ProgressCircle.Circle
            style={
              {
                "--size": "160px",
                "--thickness": "14px",
              } as React.CSSProperties
            }
          >
            <ProgressCircle.Track />
            <ProgressCircle.Range strokeLinecap="round" stroke="green" />
          </ProgressCircle.Circle>

          {/* Value text centered inside the circle */}
          <AbsoluteCenter>
            <ProgressCircle.ValueText fontSize="3xl" />
          </AbsoluteCenter>
        </ProgressCircle.Root>
      </CustomCard>
    </Box>
  );
}
