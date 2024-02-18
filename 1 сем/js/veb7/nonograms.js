document.addEventListener('DOMContentLoaded', function() {
    const cells = document.querySelectorAll('.box');
    let isDragging = false;
  
    cells.forEach(cell => {
      cell.addEventListener('click', function(event) {
        event.preventDefault();
        alert('Ви натиснули на комірку!');
  
        // Завдання II: Додавання класу filled до клікнутої комірки
        cell.classList.toggle('filled');
      });
  
      cell.addEventListener('mousedown', function() {
        isDragging = true;
      });
  
      cell.addEventListener('mouseover', function() {
        if (isDragging) {
          cell.classList.toggle('filled');
        }
      });
    });
  
    document.addEventListener('mouseup', function() {
      isDragging = false;
    });
  
    // Завдання V: Додавання кнопки "Очистити"
    const clearButton = document.createElement('button');
    clearButton.textContent = 'Очистити';
    clearButton.addEventListener('click', function() {
      cells.forEach(cell => {
        cell.classList.remove('filled');
      });
      // Можна додати підтвердження за потреби
    });
    document.body.appendChild(clearButton);
  });
  