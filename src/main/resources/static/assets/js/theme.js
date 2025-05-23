function toggleTheme() {
    document.body.classList.toggle('dark');
}

window.addEventListener('load', () => {
    const loader = document.getElementById('loader');
    loader.classList.add('hidden');
    setTimeout(() => loader.remove(), 500);
});