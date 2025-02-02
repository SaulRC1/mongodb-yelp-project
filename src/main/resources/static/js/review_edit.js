function saveReview()
{
    let reviewEditTextArea = document.getElementsByClassName("edit-review-text-area")[0];
    
    let newText = reviewEditTextArea.value;
    let currentReviewId = document.getElementById("current-review-id").textContent;
    
    fetch(("/edit-review/" + currentReviewId), 
    {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({newText: newText}),
    })
    .then(response => response.json())
    .then(data => 
        {
            alert(data.message);
            window.location.href = "/edit-review/" + currentReviewId;
        })
    .catch(error => alert("Error: " + error.error));
}

function cancel()
{
    window.location.href = "/reviews?page=1";
}


