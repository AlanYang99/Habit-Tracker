import { Box, HStack, Icon, Text } from "@chakra-ui/react";
import CustomCard from "./CustomCard";
import { ImFire } from "react-icons/im";
import { SlBadge } from "react-icons/sl";
import { FaHeartCircleCheck } from "react-icons/fa6";
import { type IconType } from "react-icons";

export default function GameSummary() {
  return (
    // 1) Parent with an explicit height
    <Box w="100%" h="100%" p={4}>
      <HStack
        h="100%" // 2) Fill the parent’s height
        w="100%"
        align="stretch" // children will stretch to HStack’s height
        justify="start"
        textAlign="start"
        p={8}
      >
        {/* Fire Streak */}
        <Figure
          icon={ImFire}
          title="Current Streak:"
          value="1 Day"
          color="red.500"
        />
        <Figure icon={SlBadge} title="Badges:" value="1" color="gold" />
        <Figure
          icon={FaHeartCircleCheck}
          title="Completed Habits:"
          value="30"
          color="green.300"
        />
      </HStack>
    </Box>
  );
}

interface FigureProps {
  icon: IconType;
  title: string;
  value: string | number;
  color: string;
}

const Figure: React.FC<FigureProps> = ({ icon, title, value, color }) => {
  return (
    <Box flex="1" display="flex" alignItems="center" justifyContent="center">
      {/* 3) Icon fills 100% of parent Box height */}
      <Icon as={icon} color={color} boxSize="clamp(3rem, 5rem, 7rem)" />
      <Box>
        <Text ml={3} color="teal" textAlign="center" fontSize={["md", "lg"]}>
          {title}
        </Text>
        <Text ml={3} color="teal" textAlign="center" fontSize={["md", "lg"]}>
          {value}
        </Text>
      </Box>
    </Box>
  );
};
