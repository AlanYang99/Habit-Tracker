import { Box, Heading, HStack, Icon } from "@chakra-ui/react";
import { GiSpikedDragonHead } from "react-icons/gi";
import CustomCard from "./CustomCard";

export default function Welcome() {
  return (
    <Box w="100%" h="100%">
      {/* Parent defines size */}
      <HStack
        w="100%"
        h="100%"
        justify="space-between"
        align="center"
        pl={4}
        pr={2}
      >
        <Heading
          as="h1"
          fontWeight="bold"
          textAlign="center"
          textShadow="2px 2px 4px rgba(0, 0, 0, 0.3)"
          fontSize={{ base: "2xl", md: "4xl", lg: "6xl" }}
          lineHeight="1"
          color="gray.800"
        >
          Welcome Back Andrew to Habit Tracker
        </Heading>

        <Icon
          as={GiSpikedDragonHead}
          boxSize="clamp(2rem, 10vw, 10vw)"
          color="teal.500"
          transform="scaleX(-1)"
        />
      </HStack>
    </Box>
  );
}
