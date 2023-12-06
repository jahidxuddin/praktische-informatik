function getCookie(cookieName) {
  var cookieArray = document.cookie.split(";");

  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i].trim();

    if (cookie.startsWith(cookieName + "=")) {
      var cookieValue = decodeURIComponent(
        cookie.substring(cookieName.length + 1)
      );
      return cookieValue;
    }
  }

  return null;
}

const userId = getCookie("user_id");

const url = new URL(window.location.href);
const chatId = url.searchParams.get("chat_id");

const chat = document.querySelector("#chat");
const eventSource = new EventSource(
  "/class-chat/message.php?chat_id=" + chatId
);

const messages = [];
eventSource.onmessage = (event) => {
    const eventData = JSON.parse(event.data);
    if (eventData.length > 0) {
        for (let i = 0; i < eventData.length; i++) {
            const data = eventData[i];
            const messageId = data["id"];
            const message = data["message_content"];

            if (!messages.includes(messageId)) {
                if (parseInt(data["user_id"]) === parseInt(userId)) {
                    if (i === eventData.length - 1) {
                      chat.innerHTML += `
                      <div class="flex justify-end">
                          <div class="rounded-lg flex items-center gap-3 border p-3 max-w-xs">
                              <img src="./public/icons/person-circle-outline.svg" alt="Avatar" height="32" width="32">
                              <span>${message}</span>
                          </div>
                      </div>
                      <div id="scroll-target"></div>
                      `;
                      document.querySelector('#scroll-target').scrollIntoView();
                    } else {
                      chat.innerHTML += `
                      <div class="flex justify-end">
                          <div class="rounded-lg flex items-center gap-3 border p-3 max-w-xs">
                              <img src="./public/icons/person-circle-outline.svg" alt="Avatar" height="32" width="32">
                              <span>${message}</span>
                          </div>
                      </div>
                      `;
                    }
                } else {
                  if (i === eventData.length - 1) {
                    chat.innerHTML += `
                    <div class="flex justify-start">
                        <div class="rounded-lg flex items-center gap-3 border p-3 max-w-xs">
                            <img src="./public/icons/person-circle-outline.svg" alt="Avatar" height="32" width="32">
                            <span>${message}</span>
                        </div>
                    </div>
                    <div id="scroll-target"></div>
                    `;
                    document.querySelector('#scroll-target').scrollIntoView();
                  } else {
                    chat.innerHTML += `
                    <div class="flex justify-start">
                        <div class="rounded-lg flex items-center gap-3 border p-3 max-w-xs">
                            <img src="./public/icons/person-circle-outline.svg" alt="Avatar" height="32" width="32">
                            <span>${message}</span>
                        </div>
                    </div>
                    `;
                  }
                }

                messages.push(messageId);
            }
        }
    }
};

