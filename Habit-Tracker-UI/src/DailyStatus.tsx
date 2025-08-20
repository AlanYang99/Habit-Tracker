import { Box, Text } from "@chakra-ui/react";
import { FaRegFaceSadCry } from "react-icons/fa6";
import { FaRegSmile } from "react-icons/fa";
import CustomCard from "./CustomCard";

export default function DailyStatus() {
  const streak = 0;

  var status;

  if (streak === 0) {
    status = (
      <Box display="flex" flexDirection="row" alignItems="center">
        <FaRegFaceSadCry style={{ marginRight: "10px" }} />
        <Text
          fontSize={{ base: "md", md: "lg", lg: "2xl" }}
          fontWeight="semibold"
        >
          No Streak
        </Text>
      </Box>
    );
  } else if (streak === 1) {
    status = (
      <Box display="flex" flexDirection="row" alignItems="center">
        <FaRegSmile />
        <Text
          fontSize={{ base: "md", md: "lg", lg: "2xl" }}
          fontWeight="semibold"
        >
          You have a streak of 1 day!
        </Text>
      </Box>
    );
  }
  return <CustomCard width="35vw">{status}</CustomCard>;
}
