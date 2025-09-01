import { Flex, Box, Text, Button, IconButton, Heading } from "@chakra-ui/react";
import { RiMailLine } from "react-icons/ri";
import { useNavigate } from "react-router";
export default function SideBar() {
  const navigate = useNavigate();
  return (
    <>
      <Flex
        as="aside"
        position="sticky"
        w={["80px", "100px", "120px", "140px"]}
        minH="95vh"
        bg="gray.800"
        color="white"
        direction="column"
        pt={3}
        gap={1}
        top="5vh"
        flexShrink={0}
      >
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
          onClick={() => {
            navigate("/dashboard");
          }}
        >
          <RiMailLine /> Dashboard
        </Button>
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
          onClick={() => {
            navigate("/dueHabits");
          }}
        >
          <RiMailLine /> Due Habits
        </Button>
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
          onClick={() => {
            navigate("/habits");
          }}
        >
          <RiMailLine /> Habits
        </Button>
      </Flex>
    </>
  );
}
