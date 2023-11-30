// components/FooterStyles.js

import styled from "styled-components";

export const Box = styled.div`
  padding: 0.1% 2.5%; 
  background: grey;
  bottom: 0;
  width: 95%;

  @media (max-width: 1000px) {
    // padding: 70px 30px;
  }
`;

export const FooterContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  max-width: 1000px;
  margin: 0 auto;
`;

export const Column = styled.div`
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-left: 15px; /* Adjust the left margin */
`;

export const Row = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(185px, 1fr));
  grid-gap: 10px; /* Adjust the grid gap */

  @media (max-width: 1000px) {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
`;

export const FooterLink = styled.a`
  color: #fff;
   margin: 0; /* Set margin to zero */
  padding: 0; /* Set padding to zero */
  margin-bottom: 10px;
  font-size: 12px;
  text-decoration: none;
  background: transparent;
  &:hover {
    color: green;
    background: transparent;
    transition: 200ms ease-in;
  }
`;

export const Heading = styled.p`
  font-size: 15px;
  color: #fff;
  margin-bottom: 2px; /* Adjust the bottom margin */
  font-weight: bold;
`;
