document.addEventListener('DOMContentLoaded', () => {
    const container = document.querySelector('.train-container');
    const track = document.querySelector('.train-track');

    const cards = container.innerHTML;
    container.insertAdjacentHTML('beforeend', cards);

    function setupWidth() {
        const containerWidth = container.scrollWidth / 2;
        container.style.width = `${containerWidth}px`;
        track.style.width = `${containerWidth * 2}px`;
    }

    setupWidth();
    window.addEventListener('resize', setupWidth);

    function setSpeed(speed) { // speed - секунд на полный цикл
        container.style.animationDuration = `${speed}s`;
    }

    setSpeed(60);
});