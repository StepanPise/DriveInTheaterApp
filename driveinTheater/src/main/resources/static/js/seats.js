function selectSeat(event, el) {
    event.preventDefault();

    if (el.classList.contains('taken')) return;

    document.querySelectorAll('.spot.free.selected').forEach(e => {
        e.classList.remove('selected');
    });

    el.classList.add('selected');
    document.getElementById('selectedSpot').value = el.getAttribute('data-spot');

    document.getElementById('errorMessage').style.display = 'none';
}

function validateForm() {
    const selectedSpot = document.getElementById('selectedSpot').value;

    if (!selectedSpot) {
        document.getElementById('errorMessage').style.display = 'block';
        return false;
    }

    return true;
}
