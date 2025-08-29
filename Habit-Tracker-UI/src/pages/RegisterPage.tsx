import {
  Center,
  Fieldset,
  Stack,
  Field,
  Input,
  Separator,
  Button,
} from "@chakra-ui/react";
import { toaster } from "@/components/ui/toaster";

import { useEffect, useRef } from "react";
import {
  Form,
  useActionData,
  useNavigation,
  useNavigate,
  useSubmit,
} from "react-router";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";

export default function RegisterPage() {
  const navigation = useNavigation();
  const navigate = useNavigate();
  const formRef = useRef(null);
  const submit = useSubmit();
  const actionData = useActionData();

  const isSubmitting = navigation.state === "submitting";

  useEffect(() => {
    if (actionData?.errors?.message) {
      toaster.error({
        title: "Check your registration Info",
        description: actionData?.errors?.message,
      });
    } else if (actionData?.errors) {
      toaster.error({
        title: "Check your registration Info",
        description: "Please check your registration fields",
      });
    } else if (actionData?.success) {
      toaster.success({
        title: "Successful Registration",
        description: "You have successfully registered your account",
      });
      const timer = setTimeout(() => {
        navigate("/login");
      }, 2000);

      return () => clearTimeout(timer);
    }
  }, [actionData]);

  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    const formData =
      formRef.current !== null ? new FormData(formRef.current) : null;

    if (!formData || validatePasswords(formData)) {
      return;
    }

    submit(formData, { method: "POST" });
  };

  const validatePasswords = (formData: FormData) => {
    if (formData.get("password") !== formData.get("confirmPassword")) {
      toaster.error({
        title: "Password Error",
        description: "The password fields dont match",
      });
      return true;
    }
    return false;
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
        h={["600px"]}
        w={["250px", "350px", "500px"]}
        bg="whiteAlpha.500/80"
        rounded="4xl"
      >
        <Center width="100%" h="100%">
          <Form
            method="POST"
            onSubmit={handleSubmit}
            ref={formRef}
            style={{ width: "80%" }}
          >
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
                <Field.Root
                  colorPalette="teal"
                  required
                  invalid={actionData?.errors?.username}
                >
                  <Field.Label>
                    Username
                    <Field.RequiredIndicator />
                  </Field.Label>
                  <Input name="username" />
                  <Field.ErrorText>
                    {actionData?.errors?.username}
                  </Field.ErrorText>
                </Field.Root>

                <Field.Root colorPalette="teal" required>
                  <Field.Label>
                    Name
                    <Field.RequiredIndicator />
                  </Field.Label>
                  <Input name="name" />
                </Field.Root>

                <Field.Root colorPalette="teal" required>
                  <Field.Label>
                    Email
                    <Field.RequiredIndicator />
                  </Field.Label>
                  <Input name="email" type="email" />
                </Field.Root>

                <Field.Root
                  colorPalette="teal"
                  required
                  invalid={actionData?.errors?.password}
                >
                  <Field.Label>
                    Password
                    <Field.RequiredIndicator />
                  </Field.Label>
                  <Input name="password" type="password" />
                  <Field.ErrorText>
                    {actionData?.errors?.password}
                  </Field.ErrorText>
                </Field.Root>

                <Field.Root colorPalette="teal" required>
                  <Field.Label>
                    Confirm Password
                    <Field.RequiredIndicator />
                  </Field.Label>
                  <Input name="confirmPassword" type="password" />
                </Field.Root>
              </Fieldset.Content>
              <Button type="submit" alignSelf="center" disabled={isSubmitting}>
                {isSubmitting ? "Registering" : "Register"}
              </Button>
            </Fieldset.Root>
          </Form>
        </Center>
      </Center>
    </Center>
  );
}

export async function registerAction({ request }: { request: Request }) {
  const formData = await request.formData();

  const registerData = {
    username: formData.get("username"),
    password: formData.get("password"),
    email: formData.get("email"),
    name: formData.get("name"),
  };

  try {
    const response = await apiClient.post("/auth/register", registerData);
    return { success: true };
  } catch (error) {
    const axiosError = error as AxiosError;

    if (axiosError.response?.status === 400) {
      console.log(axiosError.response);
      return { success: false, errors: axiosError.response?.data };
    }
    throw new Response("Failed to submit your message. Please try again.", {
      status: axiosError.status || 500,
    });
  }
}
