document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('emailFormDto');
    const submitBtn = form.querySelector('button[type="submit"]');

    submitBtn.innerHTML = '<span class="btn-text">Send</span><span class="spinner-border spinner-border-sm" style="display: none;" aria-hidden="true"></span>';

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        submitBtn.disabled = true;
        submitBtn.querySelector('.btn-text').style.display = 'none';
        submitBtn.querySelector('.spinner-border').style.display = 'inline-block';

        const formData = new FormData(form);

        fetch(form.action, {
            method: 'POST',
            body: formData,
            headers: {
                'Accept': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok');
            })
            .then(data => {
                if (data.success) {
                    showAlert('success', data.message || 'Thank you for your message! We will contact you soon.');
                    form.reset();
                } else {
                    showAlert('danger', data.message || 'An error occurred. Please try again.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                showAlert('danger', 'An error occurred. Please try again.');
            })
            .finally(() => {
                submitBtn.disabled = false;
                submitBtn.querySelector('.btn-text').style.display = 'inline-block';
                submitBtn.querySelector('.spinner-border').style.display = 'none';
            });
    });

    function showAlert(type, message) {
        const oldAlert = document.querySelector('.form-alert');
        if (oldAlert) oldAlert.remove();

        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type} form-alert mt-3`;
        alertDiv.textContent = message;

        form.parentNode.insertBefore(alertDiv, form.nextSibling);

        setTimeout(() => {
            alertDiv.remove();
        }, 5000);
    }
});