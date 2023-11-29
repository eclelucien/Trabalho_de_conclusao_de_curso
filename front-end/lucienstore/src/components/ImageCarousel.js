import React from 'react';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import '../App.css';


function ImageCarousel() {
    return (
        <div>
            <Carousel showArrows={true} showThumbs={false} interval={3000}>
                <div>
                    <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/17/16895783130a5b4a7ee74d58ae920e7a8a8f80c6d7_thumbnail_2000x.webp" alt="ImageSlide 2" />
                </div>
                <div>
                    <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/10/1688960268466f26d41b9e612de630272752d840d2_thumbnail_2000x.webp" alt="ImageSlide 1" />
                </div>
                <div>
                    <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/17/16895783130a5b4a7ee74d58ae920e7a8a8f80c6d7_thumbnail_2000x.webp" alt="ImageSlide 2" />
                </div>
            </Carousel>
        </div>
    );
}

export default ImageCarousel;
