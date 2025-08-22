import { Link as ChakraLink } from "@chakra-ui/react";
import { Link as RouterLink } from "react-router";

import type { LinkProps as ChakraLinkProps } from "@chakra-ui/react";
import type { LinkProps as RouterLinkProps } from "react-router";

// Combine Chakra ButtonProps with React Router's LinkProps
type TextLinkProps = ChakraLinkProps & RouterLinkProps;

const TextLink = (props: TextLinkProps) => {
  return <ChakraLink as={RouterLink} {...props} />;
};

export default TextLink;
