// Script to invoke Visual Studio Code editor from within WinGHCi
// Pepe Gallardo, 2018



// Access different ActiveX objects needed in rest of script
var wss = new ActiveXObject("WScript.Shell");

var sfso = new ActiveXObject("Scripting.FileSystemObject")

// Define alert function for debug purposes
alert = function(s){ WScript.Echo(s) }






// fullEditorPath should be the full path to your atom executable.
// It is usually in your Program Files folder, so we try to obtain
// the path in this way:


var programFiles = wss.ExpandEnvironmentStrings("%ProgramFiles%")

var suffix = "\\Microsoft VS Code\\code.exe"

var fullEditorPath = programFiles + suffix

if (!sfso.FileExists(fullEditorPath)) {
	programFiles = wss.ExpandEnvironmentStrings("%ProgramFiles(x86)%")
	fullEditorPath = programFiles + suffix
	
	if (!sfso.FileExists(fullEditorPath)) {	
		alert("Visual Studio Code executable not found at "+fullEditorPath+". Please edit WinGHCiVSCode.js script and redefine 'fullEditorPath' variable accordingly.")
		WScript.Quit(1)
	}		
}

// In case the path is different in your system, redefine fullEditorPath variable
// so that it represents the full path to your Visual Studio Code executable (code.exe).
// Please note double backslash characters.

// var fullEditorPath = "C:\\Program Files (x86)\\Microsoft VS Code\\code.exe"



var editorWindowTittle = "Visual Studio Code"

var fileName = WScript.arguments(0)

var fullFileName = sfso.GetAbsolutePathName(fileName)

// true if a text line in file to go to is provided
var fileLineProvided = (WScript.arguments.length > 1)

var fileLine = (fileLineProvided) ? ":" +  WScript.arguments(1).substr(1) : ""

var gotoFlag = (fileLineProvided) ? " --goto " : " " 

// The full command to invoke editor
var cmd = "\"" + fullEditorPath + "\"" + gotoFlag + "\"" + fullFileName + "\"" + fileLine

wss.run(cmd)

// alert(cmd)


// Bring editor window to foreground
wss.AppActivate(editorWindowTittle)

// returning 1 makes winGHCi not issuing a "reload" command
WScript.Quit(1)  

