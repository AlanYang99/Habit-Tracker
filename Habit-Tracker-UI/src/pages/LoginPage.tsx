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
import { Form, useActionData, useNavigate } from "react-router";
import TextLink from "../components/TextLink";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";
import { useEffect } from "react";
import { toaster } from "@/components/ui/toaster";
import { useAuth } from "@/store/AuthContext";

export default function LoginPage() {
  const actionData = useActionData();
  const navigate = useNavigate();
  const { isAuthenticated, loginSuccess } = useAuth();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/dashboard");
    }
    if (actionData?.errors?.message) {
      toaster.error({
        title: "Check your registration Info",
        description: actionData?.errors?.message,
      });
    } else if (actionData?.success) {
      console.log("login");
      loginSuccess(actionData?.token, actionData?.user);
      toaster.success({
        title: "Successful Login",
        description: "You have login successfully",
      });
      console.log(actionData?.success);
      const timer = setTimeout(() => {
        navigate("/dashboard");
      }, 2000);
      return () => clearTimeout(timer);
    }
  }, [actionData]);
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
        <Form method="POST" style={{ width: "80%" }}>
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
              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Username
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="username" />
              </Field.Root>

              <Field.Root colorPalette="teal" required>
                <Field.Label>
                  Password
                  <Field.RequiredIndicator />
                </Field.Label>
                <Input name="password" type="password" />
              </Field.Root>
            </Fieldset.Content>
            <Button type="submit" alignSelf="center">
              Submit
            </Button>
            <Separator />
            <Text textAlign="center">
              Don't have an account? Join us! Register
              <TextLink to="/register" colorPalette="red">
                here
              </TextLink>
              to get started.
            </Text>
          </Fieldset.Root>
        </Form>
      </Center>
    </Center>
  );
}

export async function loginAction({ request }: { request: Request }) {
  const formData = await request.formData();

  const loginData = {
    username: formData.get("username"),
    password: formData.get("password"),
  };

  try {
    const response = await apiClient.post("/auth/login", loginData);
    const { message, user, token } = response.data;
    return { success: true, message, user, token };
  } catch (error) {
    const axiosError = error as AxiosError;

    if (axiosError.response?.status === 400) {
      return {
        success: false,
        errors: { message: "Invalid username or password" },
      };
    }
    throw new Response("Failed to submit your message. Please try again.", {
      status: axiosError.status || 500,
    });
  }
}
