<%-- 
    Document   : login
    Created on : Dec 5, 2024, 10:58:51 PM
    Author     : Leptop
--%>
<%
    String authenticatedUser = (String) session.getAttribute("authenticatedUser");
    if(authenticatedUser != null){
        response.sendRedirect("index.jsp");
        return;
    }
%>
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

    <body class=bg-gray-700 text-white">
        <%@include file="WEB-INF/nav_auth.jsp" %>

        <div class="flex items-center justify-center min-h-screen px-4">
            <div class="flex w-full max-w-6xl items-center justify-between">
                <div class="w-1/3 bg-cover bg-center h-96 rounded-lg"
                     style="background-image: url('anakkelas.jpg');">
                </div>

                <div class="w-1/12"></div>

                <div class="w-1/2 bg-black shadow-md rounded-lg p-8">
                    <h1 class="text-3xl font-bold mb-6 text-center bg-gradient-to-r from-blue-500 to-purple-600 text-transparent bg-clip-text">
                        Welcome Back
                    </h1>

                    <form class="flex flex-col space-y-4" action="Login" method="POST">
                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium text-white">Email</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            class="p-2 w-full rounded border border-gray-300 bg-white text-black focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder=""
                            required
                        />
                    </div>

                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium text-white">Password</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            class="p-2 w-full rounded border border-gray-300 bg-white text-black focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder=""
                            required
                        />
                    </div>
                </form>


                        <div class="flex justify-between items-center">
                            <label class="inline-flex items-center">
                                <input type="checkbox" class="form-checkbox text-blue-500" />
                                <span class="ml-2 text-sm text-white">Ingat Saya</span>
                            </label>
                            <a href="forgotPassword.html" class="text-sm text-white hover:text-blue-500"
                                >Lupa Kata Sandi?</a
                            >
                        </div>
                        <div class="flex justify-center mt-8">
                            <a href="page1.html">
                            <button
                                class="bg-gradient-to-r from-blue-500 to-purple-600 px-5 py-3 rounded-full text-2xl text-white"
                            >
                                Login
                            </button>

                        </div>

                        <p class="text-center mt-4 text-white">
                            Belum punya akun?
                            <a href="register.jsp" class="text-white hover:text-blue-500">Daftar di sini</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

