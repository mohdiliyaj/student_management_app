// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
	'use strict'

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	const forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.from(forms).forEach(form => {
		form.addEventListener('submit', event => {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}

			form.classList.add('was-validated')
		}, false)
	})
})()

document.addEventListener('DOMContentLoaded', () => {
	"use strict";

	/**
   * Sticky Header on Scroll
   */
	const selectHeader = document.querySelector('#header');
	if (selectHeader) {
		let headerOffset = selectHeader.offsetTop;
		let nextElement = selectHeader.nextElementSibling;

		const headerFixed = () => {
			if ((headerOffset - window.scrollY) <= 0) {
				selectHeader.classList.add('sticked');
				if (nextElement) nextElement.classList.add('sticked-header-offset');
			} else {
				selectHeader.classList.remove('sticked');
				if (nextElement) nextElement.classList.remove('sticked-header-offset');
			}
		}
		window.addEventListener('load', headerFixed);
		document.addEventListener('scroll', headerFixed);
	}
});  