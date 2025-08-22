import {
  Center,
  Fieldset,
  Stack,
  Field,
  Input,
  Separator,
  Button,
} from "@chakra-ui/react";

export default function RegisterPage() {
  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    // Handle the form data in state
    console.log("Form submitted:");
  };

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
        <form onSubmit={(event) => handleSubmit(event)}>
          <Fieldset.Root size="lg" maxW="md">
            <Stack>
              <Fieldset.Legend
                textAlign="center"
                fontFamily="sans-serif"
                fontSize={["xl", "2xl", "3xl"]}
                fontWeight="semibold"
              >
                Register
              </Fieldset.Legend>
            </Stack>
            <Separator />
            <Fieldset.Content>
              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Username
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="username" />
              </Field.Root>

              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Email
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="email" type="email" />
              </Field.Root>

              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Password
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="password" type="password" />
              </Field.Root>

              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Confirm Password
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="confirm password" type="password" />
              </Field.Root>
            </Fieldset.Content>
            <Button type="submit" alignSelf="center">
              Register
            </Button>
          </Fieldset.Root>
        </form>
      </Center>
    </Center>
  );
}
