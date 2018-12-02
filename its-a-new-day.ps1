$client = New-Object System.Net.WebClient

$dateStr = Read-Host 'What is the date in the Advent Calendar'
[int]$date = [convert]::ToInt32($dateStr, 10)

If ($date -gt 25 -Or $date -lt 1) {
    Write-Host "Invalid Date, 1-25 pls"
    Exit
}

$cookie = Read-Host 'Enter Session Cookie for www.adventofcode.com'
$client.Headers.Add([System.Net.HttpRequestHeader]::Cookie, "session=" + $cookie)

Invoke-Expression "lein new app day-$date"

$client.DownloadFile("http://adventofcode.com/2018/day/$date/input", 'input')

Copy-Item "input" -Destination "day-$date/resources"