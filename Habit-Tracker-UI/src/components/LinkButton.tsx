import { Button } from "@chakra-ui/react";
import { Link } from "react-router";

import type { ButtonProps } from "@chakra-ui/react";
import type { LinkProps } from "react-router";

// Combine Chakra ButtonProps with React Router's LinkProps
type LinkButtonProps = ButtonProps & LinkProps;

const LinkButton = (props: LinkButtonProps) => {
  return <Button as={Link} {...props} />;
};

export default LinkButton;
