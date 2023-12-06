<?php
if (isset($_COOKIE["user_id"])) {
    header("Location: /class-chat/index.php");
    exit;
}

require_once("../database/database.php");

function sanitize($input)
{
    $input = strip_tags($input);
    return htmlspecialchars($input, ENT_QUOTES, 'UTF-8');
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    if (!isset($_POST["username"]) || !isset($_POST["email"]) || !isset($_POST["password"])) {
        http_response_code(400);
        $response = "incorrect username, email or password";
        header("Location: /class-chat/auth/register.php?error=" . urlencode($response));
        exit;
    }

    $username = sanitize($_POST["username"]);
    $email = sanitize($_POST["email"]);
    $password = sanitize($_POST["password"]);

    if (empty($username) || empty($email) || empty($password)) {
        http_response_code(400);
        $response = "incorrect username, email or password";
        header("Location: /class-chat/auth/register.php?error=" . urlencode($response));
        exit;
    }

    $query = "SELECT * FROM users WHERE email = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $email);
    $stmt->execute();

    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        http_response_code(409);
        $response = "user already exists in the database";
        header("Location: /class-chat/auth/register.php?error=" . urlencode($response));
        exit;
    }

    $passwordHash = password_hash($password, PASSWORD_DEFAULT);

    $sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

    $stmt = $conn->prepare($sql);

    $stmt->bind_param("sss", $username, $email, $passwordHash);

    if (!$stmt->execute()) {
        http_response_code(500);
        $response = "failed to insert user";
        header("Location: /class-chat/auth/register.php?error=" . urlencode($response));
        exit;
    }

    $query = "SELECT * FROM users WHERE email = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $email);
    $stmt->execute();

    $result = $stmt->get_result();

    $data = $result->fetch_assoc();

    $id = $data["id"];

    $query = "UPDATE users SET is_logged_in = 1 WHERE id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $id);
    $stmt->execute();

    // After 30 days
    $expiration = time() + (30 * 24 * 60 * 60);
    setcookie('user_id', $id, $expiration, '/');

    $stmt->close();
    $conn->close();

    header("Location: /class-chat/index.php");
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register | Class Chat</title>
    <link rel="stylesheet" href="../public/styles/style.css">
</head>

<body class="bg-black text-white w-full h-screen flex justify-center items-center">
    <main class="flex flex-col gap-10 px-10 md:p-0 w-full md:w-1/2 xl:w-1/3">
        <h1 class="text-4xl text-center font-semibold">Create an account</h1>
        <form class="flex flex-col gap-5" action="/class-chat/auth/register.php" method="post">
            <input required class="focus:outline-none p-5 rounded-lg bg-black border" placeholder="Username" type="text" name="username" id="username">
            <input required class="focus:outline-none p-5 rounded-lg bg-black border" placeholder="E-Mail" type="email" name="email" id="email">
            <input required class="focus:outline-none p-5 rounded-lg bg-black border" placeholder="Password" type="password" name="password" id="password">
            <?php if (isset($_GET["error"])) : ?>
                <span class="text-red-500 text-md"><?php echo $_GET["error"]; ?></span>
            <?php endif; ?>
            <a class="text-center pr-5 my-5 underline" href="/class-chat/auth/login.php">Already have an account?</a>
            <input class="text-xl cursor-pointer p-5 rounded-lg border" type="submit" value="Register">
        </form>
    </main>
</body>

</html>