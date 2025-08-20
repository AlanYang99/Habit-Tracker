import {
  Flex,
  Box,
  Spacer,
  IconButton,
  Menu,
  Button,
  Portal,
  Heading,
} from "@chakra-ui/react";
import { RiArrowDropDownLine } from "react-icons/ri";
import { CgProfile } from "react-icons/cg";
import { IoFitness } from "react-icons/io5";

export default function Header() {
  return (
    <>
      <Flex
        as="header"
        position="sticky"
        top="0"
        zIndex="1000"
        w="100vw"
        h="5vh"
        bg="teal.emphasized"
        p={4}
        shadow="lg"
        justify="flex-start"
        ml="auto"
        align="center"
      >
        {/* //Place holder for logo */}
        <IconButton colorPalette="teal" variant="plain">
          Habit Tracker
          <IoFitness />
        </IconButton>
        <Spacer />
        {/* <IconButton size="sm" m={4}>
          <LuArrowBigDown color="teal" />
        </IconButton>

        <IconButton size="sm" m={4}>
          <LuArrowBigDown color="teal" />
        </IconButton> */}

        <Button colorScheme="teal" variant="ghost">
          Call us
        </Button>
        <Button colorScheme="teal" variant="ghost" m={1}>
          Call us
        </Button>

        <CgProfile size="24" color="black" />
        <Box>
          <Menu.Root>
            <Menu.Trigger asChild>
              <IconButton size="sm" variant="plain">
                <RiArrowDropDownLine color="gray" />
              </IconButton>
            </Menu.Trigger>
            <Portal>
              <Menu.Positioner>
                <Menu.Content>
                  <Menu.Item value="View Profile">View Profile</Menu.Item>
                  <Menu.Item value="Logout">Logout</Menu.Item>
                </Menu.Content>
              </Menu.Positioner>
            </Portal>
          </Menu.Root>
        </Box>

        {/* <Box bg="tomato">Hello</Box>
        <Box bg="tomato">Bye</Box> */}
      </Flex>
    </>
  );
}
