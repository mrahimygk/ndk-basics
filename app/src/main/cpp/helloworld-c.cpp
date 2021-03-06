//
// Created by vincent on 5/8/21.
//

#include <android_native_app_glue.h>
#include <jni.h>

extern "C" {
void handle_cmd(android_app *pApp, int32_t cmd) {

    switch (cmd) {
        case APP_CMD_INIT_WINDOW:
            //pApp->userData = new Renderer(pApp);
            break;

        case APP_CMD_TERM_WINDOW:
            if(pApp->userData){
                /*auto *pRenderer = reinterpret_cast<Renderer *>(pApp->userData);
                pApp->userData = nullptr;
                delete pRenderer;*/
            }

    }
}

void android_main(struct android_app *pApp) {
    pApp->onAppCmd = handle_cmd;
    pApp->userData;

    int events;

    android_poll_source *pSource;
    do {
        if (ALooper_pollAll(0, nullptr, &events, (void **) &pSource) >= 0) {
            if (pSource) {
                pSource->process(pApp, pSource);
            }
        }

        if(pApp->userData){
            /*auto *pRenderer = reinterpret_cast<Renderer *>(pApp->userData);
            pRenderer->render();*/
        }
    } while (!pApp->destroyRequested);
}

}