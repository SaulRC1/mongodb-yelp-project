function moveToFirstPage()
{
    window.location.href = "/tips?page=1";
}

function moveBackwardsPage()
{
    tipList = document.getElementsByClassName("tip-card");
    
    firstTip = tipList[0];
    
    date = firstTip.getElementsByClassName("tip-card-date-hidden")[0].textContent;
    
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    
    currentPage = paginationPageDisplay.split(" ")[0];
    
    if(currentPage === "1")
    {
        return;
    }
    
    backwardsPage = Number(currentPage) - 1;
    
    window.location.href = "/tips?page=" + backwardsPage + "&date=" + date + "&backwards=true";
}

function moveForwardPage()
{
    tipList = document.getElementsByClassName("tip-card");
    
    lastTip = tipList[tipList.length - 1];
    
    date = lastTip.getElementsByClassName("tip-card-date-hidden")[0].textContent;
    
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    
    currentPage = paginationPageDisplay.split(" ")[0];
    lastPage = Number(paginationPageDisplay.split(" ")[2]);
    
    forwardPage = Number(currentPage) + 1;
    
    if(forwardPage >= lastPage)
    {
        return;
    }
    
    window.location.href = "/tips?page=" + forwardPage + "&date=" + date + "&backwards=false";
}

function moveToLastPage()
{
    window.location.href = "/tips?page=last";
}
