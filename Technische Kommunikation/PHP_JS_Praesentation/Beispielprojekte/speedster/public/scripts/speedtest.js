async function startSpeedtest() {
  document.querySelector("#status").textContent = "...";
  const downloadTime = await testDownload();
  const uploadTime = await testUpload();
  const pingTime = await testPing();
  document.querySelector("#status").textContent = "Run"

  document.querySelector("#download").textContent = downloadTime + "mbps";
  document.querySelector("#upload").textContent = uploadTime + "mbps";
  document.querySelector("#ping").textContent = pingTime + "ms";
}

async function testDownload() {
  const startTime = new Date().getTime();

  return await fetch("/speedster-test/api/download.php")
    .then((_res) => {
      const endTime = new Date().getTime();
      return (4000 / (endTime - startTime)).toFixed(2);
    })
    .catch((error) => {
      console.error("Error:", error);
      return -1;
    });
}

async function testUpload() {
  const formData = new FormData();
  formData.append("file", "../data/data_500mb.test");

  const startTime = new Date().getTime();

  return await fetch("/speedster-test/api/upload.php", {
    method: "POST",
    body: formData,
  })
    .then((_res) => {
      const endTime = new Date().getTime();
      return (4000 / (endTime - startTime)).toFixed(2);
    })
    .catch((error) => {
      console.error("Error:", error);
      return -1;
    });
}

async function testPing() {
  const startTime = new Date().getTime();

  return await fetch("/speedster-test/api/ping.php")
    .then((_res) => {
      const endTime = new Date().getTime();
      return endTime - startTime;
    })
    .catch((error) => {
      console.error("Error:", error);
      return -1;
    });
}
