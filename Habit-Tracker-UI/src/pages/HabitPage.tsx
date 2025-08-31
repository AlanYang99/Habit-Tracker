import {
  Flex,
  Grid,
  GridItem,
  Box,
  Menu,
  Avatar,
  Portal,
  Button,
  Field,
  Stack,
  Separator,
  Input,
  Dialog,
  NativeSelect,
  For,
  SimpleGrid,
  HStack,
  Checkbox,
  Heading,
  Fieldset,
  Card,
  Text,
  Image,
} from "@chakra-ui/react";
import {
  Form,
  useActionData,
  useNavigation,
  useNavigate,
  useSubmit,
} from "react-router";
import { useLoaderData } from "react-router";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";
import { useEffect, useState } from "react";
import DatePicker, { DateObject, type Value } from "react-multi-date-picker";

interface HabitProps {
  id: number;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  daysOfWeek?: DayOfWeek[];
  scheduledDates?: string[];
}

interface HabitCardProps {
  habit: HabitProps;
}

type DayOfWeek =
  | "MONDAY"
  | "TUESDAY"
  | "WEDNESDAY"
  | "THURSDAY"
  | "FRIDAY"
  | "SATURDAY"
  | "SUNDAY";

type HabitCardCombinedProps = HabitCardProps & { m?: number };

const FREQUENCY_OPTIONS = [
  { label: "Daily", key: "DAILY" },
  { label: "Weekly", key: "WEEKLY" },
  { label: "Certain Days", key: "CALENDAR_DAY" },
];

export default function HabitPage() {
  const { habitsData } = useLoaderData<{
    habitsData: HabitProps[];
  }>();

  const actionData = useActionData();

  const [habitType, setHabitType] = useState("Daily");
  const [days, setDays] = useState<string[]>([]);
  const [selectedDates, setSelectedDates] = useState<DateObject[]>([]);
  const [open, setOpen] = useState(false);

  useEffect(() => {
    setOpen(false);
    console.log(habitsData);
  }, [actionData]);

  const daysOfWeek = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
  ];

  const toggleDay = (day: string) => {
    setDays((prev) =>
      prev.includes(day) ? prev.filter((d) => d !== day) : [...prev, day]
    );
  };

  return (
    <Box
      h="95vh"
      w="100%"
      bgGradient="to-br"
      gradientFrom="purple.600"
      gradientVia="teal.400"
      gradientTo="blue.200"
      textAlign="center"
      overflow="auto"
    >
      <Heading size="5xl" m={3}>
        Habits List
      </Heading>

      <Dialog.Root open={open} onOpenChange={(e) => setOpen(e.open)}>
        <Dialog.Trigger asChild>
          <Button variant="outline" m={2} colorPalette="teal">
            Add Habit
          </Button>
        </Dialog.Trigger>
        <Portal>
          <Dialog.Backdrop />
          <Dialog.Positioner>
            <Dialog.Content>
              <Dialog.Header>
                <Dialog.Title>New Habit</Dialog.Title>
              </Dialog.Header>
              <Dialog.Body pb="4">
                <Stack gap="4">
                  <Form id="habit-form" method="POST">
                    <Fieldset.Root>
                      <Field.Root>
                        <Field.Label>Name</Field.Label>
                        <Input name="name" />
                      </Field.Root>
                      <Field.Root>
                        <Field.Label>Habit Type</Field.Label>
                        <NativeSelect.Root>
                          <NativeSelect.Field
                            name="type"
                            value={habitType}
                            onChange={(e) => setHabitType(e.target.value)}
                          >
                            <For each={FREQUENCY_OPTIONS}>
                              {(opt) => (
                                <option key={opt.key} value={opt.key}>
                                  {opt.label}
                                </option>
                              )}
                            </For>
                          </NativeSelect.Field>
                          <NativeSelect.Indicator />
                        </NativeSelect.Root>
                      </Field.Root>
                      {habitType !== "Certain Days" && (
                        <>
                          <Field.Root>
                            <Field.Label>Start Date</Field.Label>
                            <Input
                              placeholder="Start Date"
                              name="start-date"
                              type="Date"
                            />
                          </Field.Root>
                          <Field.Root>
                            <Field.Label>End Date</Field.Label>
                            <Input
                              placeholder="End Date"
                              name="end-date"
                              type="Date"
                            />
                          </Field.Root>
                        </>
                      )}
                      {habitType === "Weekly" && (
                        <SimpleGrid columns={[2, 3]} w="100%">
                          <For each={daysOfWeek}>
                            {(day) => {
                              const isChecked = days.includes(day);
                              return (
                                <Stack align="flex-start" flex="1" key={day}>
                                  <Checkbox.Root
                                    m={1}
                                    checked={isChecked}
                                    onCheckedChange={() => toggleDay(day)}
                                  >
                                    <Checkbox.HiddenInput />
                                    <Checkbox.Control />
                                    <Checkbox.Label>{day}</Checkbox.Label>
                                  </Checkbox.Root>
                                </Stack>
                              );
                            }}
                          </For>
                        </SimpleGrid>
                      )}
                      {days.map((day) => (
                        <input
                          key={day}
                          type="hidden"
                          name="daysOfWeek[]"
                          value={day}
                        />
                      ))}

                      {habitType === "Certain Days" && (
                        <Field.Root>
                          <Field.Label>Select End Dates</Field.Label>
                          <Stack>
                            <Box
                              border="1px solid"
                              borderColor="gray.300"
                              borderRadius="md"
                              p="2"
                              w="100%"
                            >
                              <DatePicker
                                multiple
                                name="dates"
                                value={selectedDates}
                                onChange={setSelectedDates}
                                format="YYYY-MM-DD"
                                placeholder="Select multiple dates"
                                style={{
                                  width: "100%",
                                  padding: "8px",
                                  fontSize: "16px",
                                  borderRadius: "6px",
                                  border: "none",
                                  outline: "none",
                                }}
                              />
                            </Box>
                            <Box>
                              <strong>Selected Dates:</strong>
                              {Array.isArray(selectedDates)
                                ? selectedDates
                                    .map((d) => d.toString())
                                    .join(", ")
                                : ""}
                            </Box>
                          </Stack>
                        </Field.Root>
                      )}
                      <Field.Root>
                        <Field.Label>Description</Field.Label>
                        <Input
                          placeholder="Description"
                          name="description"
                          type="text"
                        />
                      </Field.Root>
                    </Fieldset.Root>
                  </Form>
                </Stack>
              </Dialog.Body>
              <Dialog.Footer>
                <Dialog.ActionTrigger asChild>
                  <Button variant="outline">Cancel</Button>
                </Dialog.ActionTrigger>
                <Button type="submit" form="habit-form">
                  Add
                </Button>
              </Dialog.Footer>
            </Dialog.Content>
          </Dialog.Positioner>
        </Portal>
      </Dialog.Root>
      <SimpleGrid columns={[2, 3, 3, 4]} w="100%">
        {habitsData.map((habit) => {
          return <HabitCard key={habit.id} habit={habit} />;
        })}
      </SimpleGrid>
    </Box>
  );
}

export async function habitsLoader() {
  try {
    const habits = await apiClient.get("/habit");
    return { habitsData: habits?.data };
  } catch (error) {
    const axiosError = error as AxiosError;
    throw new Response("Failed to fetch information for user", {
      status: axiosError.status || 500,
    });
  }
}

export async function addHabitAction({ request }: { request: Request }) {
  const formData = await request.formData();

  console.log(formData);
  const habitData = {
    name: formData.get("name"),
    frequency: formData.get("type"),
    startDate: formData.get("start-date"),
    endDate: formData.get("end-date"),
    description: formData.get("description"),
    dates: formData.get("dates"),
    days: formData.getAll("daysOfWeek[]"),
  };

  console.log(habitData);
  try {
    const response = await apiClient.post("/habit", habitData);
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
const HabitCard = ({ habit, m = 8 }: HabitCardCombinedProps) => {
  return (
    <Card.Root maxW="sm" overflow="hidden" m={m}>
      <Image
        src="https://media.istockphoto.com/id/1481370371/photo/portrait-of-enthusiastic-hispanic-young-woman-working-on-computer-in-a-modern-bright-office.jpg?s=612x612&w=0&k=20&c=8kNce9Ruc9F2KXvnwf0stWQXCwwQTBCrW8efrqhUIa4="
        alt="Green double couch with wooden legs"
      />
      <Card.Body gap="2">
        <Card.Title>{habit?.name}</Card.Title>
        <Card.Description>{habit?.description}</Card.Description>
        <Text textStyle="2xl" fontWeight="medium" letterSpacing="tight" mt="2">
          {/* {habit?.frequency} */}
          {/* Lets check the frequency implementation later */}
        </Text>
      </Card.Body>
      <Card.Footer gap="2">
        <Button variant="solid">View More</Button>
      </Card.Footer>
    </Card.Root>
  );
};
