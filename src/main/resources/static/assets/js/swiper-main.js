const swiper = new Swiper('.swiper', {
    direction: "horizontal",
    loop: true,

    autoplay: {
        delay: 4000,
        disableOnInteraction: false,
    },

    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },

    effect: 'creative',
    creativeEffect: {
        prev: {
            translate: [-120, 0, -200],
            opacity: 0.5
        },
        next: {
            translate: [120, 0, -200],
            opacity: 0.5
        }
    }
});