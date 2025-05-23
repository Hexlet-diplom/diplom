document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.course-page__step-toggle').forEach(button => {
        button.addEventListener('click', (e) => {
            e.stopPropagation();
            const accordionItem = button.closest('.accordion-item');
            const isActive = accordionItem.classList.contains('active');

            document.querySelectorAll('.accordion-item').forEach(item => {
                item.classList.remove('active');
            });

            if (!isActive) {
                accordionItem.classList.add('active');
            }
        });
    });
});
