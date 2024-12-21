# rsx-ij

<!-- Plugin description -->
# rsx language support for IntelliJ IDEA

rsx is a new language that combines Rust and JSX, it is designed to be used in web development, and it can be compiled to Native code for SSR.

## example

```html
---
use rsx::{ Request, Response };

async fn get_server_props(request: Request) -> Response {
    Response::json!({
        "title": "rsx project",
        "pageName": "index.html"
    })
}
---

<script>
    import Content from './content.tsx';
    const { pageName, title } = $props;
</script>

<template>
    <div>
        <h1 class="title">welcome to {pageName}</h1>
        <Content title={title}></Content>
    </div>
</template>

<style>
    .title {
        font-size: 16px;
        text-align: center;
    }
</style>

```

## notice

this plugin is still in development, and it is not recommended to use it in production.

<!-- Plugin description end -->
