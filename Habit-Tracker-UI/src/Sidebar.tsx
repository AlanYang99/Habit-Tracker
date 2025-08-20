import { Flex, Box, Text, Button, IconButton, Heading } from "@chakra-ui/react";
import { RiMailLine } from "react-icons/ri";
export default function SideBar() {
  return (
    <>
      <Flex
        as="aside"
        position="sticky"
        w="13vw"
        h="full"
        bg="gray.800"
        color="white"
        direction="column"
        pt={3}
        gap={1}
      >
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
        >
          <RiMailLine /> Email
        </Button>
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
        >
          <RiMailLine /> Email
        </Button>
        <Button
          variant="ghost"
          size="md"
          borderRadius="18px"
          mb={2}
          color="gray.200"
        >
          <RiMailLine /> Email
        </Button>
      </Flex>
    </>
  );
}
