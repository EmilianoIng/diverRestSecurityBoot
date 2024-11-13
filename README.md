# diverRestSecurityBoot
be
NativeScript Cli

Creazione di un progetto Nativescript7


1)ns create
creazione di uno scheletro di progetto vuoto
nello specifico sse si vuole creare uno scheletro nativescript utilizzante angular eseguire
ns create example-app --ng


2)ns create con l’aggiunta del flag –-template
questo crea un progetto nativescript a partire da un determinato template
ad esempio

 ns create HelloWorld --template @nativescript/template-hello-world-ts

lista completa dei template disponibili:
https://github.com/NativeScript/nativescript-app-templates/tree/master/packages

recuperare i template che terminano con ng

3)ns clean equivale in un certo senso al comando clean di maven
elimina le cartelle hooks platform e node_module

4)ns debug android 

permette di avviare il debug in cmd appare una url che incollata in chrome e da console esplorando il source si può effettuare il debug su codice

4)per installare l’applicazione nativescript sviluppata sul mio dispositivo android per prima cosa abilitare la visualizzazione di opzioni sviluppatore una volta abilitata tale opzione  entrare in questa e abilitare Debug USB connettere il dispositivo tramite usb al pc e digitare vil seguenete comando:
adb devices
e il mio dispositivo viene visualizzato

successivamente tramite il comando ns run android eseguo l’app sul mio smartphone

ns run --no-hmr --emulator

questo comando mi consente di eseguire l’app sul mio emulatore android il flag –no-hmr mi consente di riavviare l’app sull’emulatore alle modifiche 

bisogna anche impostare sul mio dispositivo android 

1) il debug tramite usb
  da impostazioni info telefono 7 tap sul n di build
2)dal play store andare sul profilo cliccare su gestisci app e dispositivo e disattivare play protect

key per api da sito https://newsapi.org/
700c5a79538442cfb103f5a48caa738f 


jdk1.8.0_231


Per modificare il nome dell’applicazione (quello che si visualizxza sullo smartphone) basta modificare (o creare il file )
C:\nativescript\fromTemplate\diverKinvey\App_Resources\Android\src\main\res\values\strings.xml
con il seguente contenuto:
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">diverKinvey</string>
    <string name="title_activity_kimera">diverKinvey</string>
</resources>


se invece si vuole copiare un progetto e fare in modo che questo risulti a se stante rispetto l’originale basta modificare il package modificando il contenuto del file

C:\nativescript\fromTemplate\diverKinvey\nativescript.config.ts

in particolare 
id: 'org.nativescript.diverKinvey',


e se presente la dipendenza di firebase nel progetto modificare il file
google-services.json

 "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:353151545662:android:8d9d22cecaff0c930c3c6f",
        "android_client_info": {
          "package_name": "org.nativescript.diverBckEnd" // nome del progetto  
        }
