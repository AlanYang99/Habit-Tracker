import {
  Center,
  Fieldset,
  Stack,
  Field,
  Input,
  Separator,
  Button,
  Text,
} from "@chakra-ui/react";
import TextLink from "../TextLink";

export default function LoginPage() {
  return (
    <Center
      h="100vh"
      w="100%"
      bgGradient="to-br"
      gradientFrom="purple.600"
      gradientVia="teal.400"
      gradientTo="blue.200"
    >
      <Center
        h={["400px", "500px", "600px"]}
        w={["200px", "350px", "500px"]}
        bg="whiteAlpha.500/80"
        rounded="4xl"
      >
        <Fieldset.Root size="lg" maxW="md">
          <Stack>
            <Fieldset.Legend
              textAlign="center"
              fontFamily="sans-serif"
              fontSize={["xl", "2xl", "3xl"]}
              fontWeight="semibold"
            >
              Login
            </Fieldset.Legend>
          </Stack>
          <Separator />
          <Fieldset.Content>
            <Field.Root colorPalette="teal">
              <Field.Label>Username</Field.Label>
              <Input name="name" />
            </Field.Root>

            <Field.Root colorPalette="teal">
              <Field.Label>Password</Field.Label>
              <Input name="password" type="password" />
            </Field.Root>
          </Fieldset.Content>
          <Button type="submit" alignSelf="center">
            Submit
          </Button>
          <Separator />
          <Text textAlign="center">
            Don't have an account? Join us! Register{" "}
            <TextLink to="/register">here</TextLink> to get started.
          </Text>
        </Fieldset.Root>
      </Center>
    </Center>
  );
}
