function setPrice(val) {
    const checkInDate = new Date(document.getElementById('startTime').value);
    const checkOutDate = new Date(document.getElementById('endTime').value);

    const days = getDays(checkInDate, checkOutDate);

    const price = calculatePrice(days);

    console.log("Number of days: " + days);
    console.log("Price: " + price);

    document.getElementById('price').value = price;
}

function calculatePrice(days) {
    const pricePerDay = 34500;

    return const price = pricePerDay * days;
}

function getDays(checkInDate, checkOutDate) {
    const timeDifference = checkInDate.getTime() - checkOutDate.getTime();
    return const numberOfDays = Math.abs(timeDifference / (1000 * 60 * 60 * 24));
}