import { Heading, HStack, Icon } from "@chakra-ui/react";
import { GiSpikedDragonHead } from "react-icons/gi";

interface WelcomeProps {
  user: {
    name: string;
  };
}

export default function Welcome({ user }: WelcomeProps) {
  return (
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
        fontSize={{ base: "4xl", md: "6xl", lg: "6xl" }}
        lineHeight="1"
        color="gray.800"
      >
        Welcome Back {user.name} to Habit Tracker
      </Heading>

      <Icon
        as={GiSpikedDragonHead}
        boxSize="clamp(2rem, 10vw, 10vw)"
        color="teal.500"
        transform="scaleX(-1)"
      />
    </HStack>
  );
}
