
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faFacebookF,
    faInstagram,
    faTwitter,
    faYoutube,
} from "@fortawesome/free-brands-svg-icons";
import {
    Box,
    FooterContainer,
    Row,
    Column,
    FooterLink,
    Heading,
} from "./FooterStyles";

const Footer = () => {
    return (
        <Box>
            <h1
                style={{
                    color: "white",
                    textAlign: "center",
                    marginTop: "2px",
                    fontSize: "24px",
                }}
            >
                All products and find great deals!
            </h1>
            <FooterContainer>
                <Row>
                    <Column>
                        <Heading>About Us</Heading>
                        <FooterLink href="#">Aim</FooterLink>
                        <FooterLink href="#">Vision</FooterLink>
                        <FooterLink href="#">Testimonials</FooterLink>
                    </Column>
                    <Column>
                        <Heading>Services</Heading>
                        <FooterLink href="#">Writing</FooterLink>
                        <FooterLink href="#">Internships</FooterLink>
                        <FooterLink href="#">Coding</FooterLink>
                        <FooterLink href="#">Teaching</FooterLink>
                    </Column>
                    <Column>
                        <Heading>Contact Us</Heading>
                        <FooterLink href="#">Uttar Pradesh</FooterLink>
                        <FooterLink href="#">Ahmedabad</FooterLink>
                        <FooterLink href="#">Indore</FooterLink>
                        <FooterLink href="#">Mumbai</FooterLink>
                    </Column>
                    <Column>
                        <Heading>Social Media</Heading>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faFacebookF} />
                            <span style={{ marginLeft: "2px" }}>Facebook</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faInstagram} />
                            <span style={{ marginLeft: "2px" }}>Instagram</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faTwitter} />
                            <span style={{ marginLeft: "2px" }}>Twitter</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faYoutube} />
                            <span style={{ marginLeft: "2px" }}>Youtube</span>
                        </FooterLink>
                    </Column>
                    <Column>
                        <Heading>More Social Media</Heading>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faFacebookF} />
                            <span style={{ marginLeft: "2px" }}>Facebook</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faInstagram} />
                            <span style={{ marginLeft: "2px" }}>Instagram</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faTwitter} />
                            <span style={{ marginLeft: "2px" }}>Twitter</span>
                        </FooterLink>
                        <FooterLink href="#">
                            <FontAwesomeIcon icon={faYoutube} />
                            <span style={{ marginLeft: "2px" }}>Youtube</span>
                        </FooterLink>
                    </Column>
                </Row>
            </FooterContainer>
        </Box>
    );
};

export default Footer;
