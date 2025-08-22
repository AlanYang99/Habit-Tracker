import { Center, Heading, VStack, Text } from "@chakra-ui/react";
import LinkButton from "../LinkButton";
import { Link as RouterLink } from "react-router";

export default function HomePage() {
  return (
    <Center
      h="100vh"
      w="100%"
      bgGradient="to-br"
      gradientFrom="purple.600"
      gradientVia="teal.400"
      gradientTo="blue.200"
    >
      <VStack>
        <Heading
          as="h1"
          fontSize={["3xl", "5xl", "7xl"]}
          fontWeight="extrabold"
          color="white"
          lineHeight="shorter"
          textAlign="center"
        >
          Welcome to Habit Tracker
        </Heading>

        <Text
          fontSize={["md", "lg", "xl"]}
          color="whiteAlpha.800"
          maxW="600px"
          alignItems="center"
        >
          Track habits, smash goals, and level up your daily routine.
        </Text>

        <LinkButton
          as={RouterLink}
          to="/login"
          size="lg"
          colorPalette="teal"
          variant="solid"
          color="white"
          fontWeight="bold"
        >
          Get Started
        </LinkButton>
      </VStack>
    </Center>
  );
}
