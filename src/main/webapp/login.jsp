<%-- 
    Document   : login
    Created on : Dec 5, 2024, 10:58:51 PM
    Author     : Leptop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login - SoundNata</title>
        <link
            href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
            rel="stylesheet"
            />
    </head>

    <body class="bg-black text-white">
        <nav class="bg-gray-800 p-4">
            <div class="container mx-auto flex justify-center">
                <h1 class="text-4xl">
                    <a href="/soundnata/" class="hover:text-gray-400">
                        Sound
                        <span class="text-blue-500">Nata</span>
                    </a>
                </h1>
            </div>
        </nav>

        <div class="flex items-center justify-center min-h-screen">
            <div class="bg-gray-400 shadow-md rounded-lg p-8 w-full max-w-md">
                <h1 class="text-3xl font-bold mb-6 text-center">Log in To SoundNata</h1>
                <form class="flex flex-col space-y-4" action="Login" method="POST">


                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium"
                               >Email</label
                        >
                        <input
                            type="email"
                            id="email"
                            name="email"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan email kamu.."
                            required
                            />
                    </div>

                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium"
                               >Password</label
                        >
                        <input
                            type="password"
                            id="password"
                            name="password"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan password kamu...."
                            required
                            />
                    </div>

                    <div class="flex justify-between items-center">
                        <label class="inline-flex items-center">
                            <input type="checkbox" class="form-checkbox text-blue-500" />
                            <span class="ml-2 text-sm">Ingat Saya</span>
                        </label>
                        <a href="forgotPassword.html" class="text-sm text-black"
                           >Lupa Kata Sandi?</a
                        >
                    </div>
                    <div class="flex justify-center mt-8">
                        <button
                            type="submit"
                            class="bg-gray-500 px-5 py-3 rounded-full text-2xl text-center"
                            >
                            Login
                        </button>
                    </div>

                    <p class="text-center mt-4">
                        Belum punya akun?
                        <a href="register.jsp" class="text-black">Daftar di sini</a>
                    </p>
                </form>
            </div>
        </div>
    </body>
</html>
