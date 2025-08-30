import { ProgressCircle, AbsoluteCenter, Card } from "@chakra-ui/react";

export default function DailyProgressCircle() {
  return (
    <Card.Root
      maxW="sm"
      background="white/60"
      rounded="4xl"
      ml="20px"
      mr="20px"
      border="2px"
      h="290px"
      width="450px"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      mb={4}
    >
      <Card.Title mb={6}>Daily Completion Rate</Card.Title>
      <ProgressCircle.Root value={30} colorPalette="green">
        <ProgressCircle.Circle
          style={
            {
              "--size": "220px",
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
    </Card.Root>
  );
}
