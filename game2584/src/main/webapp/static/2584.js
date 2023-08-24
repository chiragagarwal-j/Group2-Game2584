document.addEventListener('keydown', function(event) {
	// Check which arrow key was pressed
	let direction = '';
	switch (event.key) {
		case 'ArrowUp':
			console.log("up");
			direction = 'up';
			break;
		case 'ArrowDown':
			direction = 'down';
			break;
		case 'ArrowLeft':
			direction = 'left';
			break;
		case 'ArrowRight':
			direction = 'right';
			break;
	}
});