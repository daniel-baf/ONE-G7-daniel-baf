// Wait until the DOM is fully loaded
document.addEventListener("DOMContentLoaded", function () {
  // action listeners
  document
    .getElementById("encrypt-text-btn")
    .addEventListener("click", encryptText);
  document.getElementById("copy-btn").addEventListener("click", copyResult);
  document
    .getElementById("decrypt-text-btn")
    .addEventListener("click", decryptText);
});

const replacements = new Map([
  ["a", "ai"],
  ["e", "enter"],
  ["i", "imes"],
  ["o", "ober"],
  ["u", "ufat"],
]);

// functions
// function to encrypt the text in the textarea
function encryptText() {
  // get the current value of the input
  let text = document
    .getElementById("input-text-area")
    .value.trim()
    .toLowerCase();
  // check if text is valid
  if (!text) {
    enableEmptyResultPanel();
    return;
  }
  // split the text
  let final_result = text
    .split(" ")
    .map((word) => getEncryptedWord(word))
    .join(" ");
  displayTextInResult(final_result);
}

/*
    This function takes a word, split into array, and the concats each word and appends the equals as encrypted
    La letra "e" es convertida para "enter"
    La letra "i" es convertida para "imes"
    La letra "a" es convertida para "ai"
    La letra "o" es convertida para "ober"
    La letra "u" es convertida para "ufat"
    */
function getEncryptedWord(current_word) {
  return current_word
    .split("")
    .map((character) => replacements.get(character) || character)
    .join("");
}

// this function copies the value of the result input and save it into the clipboard
function copyResult() {
  let text_area_to_copy = document.getElementById("result-text-area");
  if (!text_area_to_copy.value) {
    return;
  }
  // continue and copy to clipboard
  navigator.clipboard.writeText(text_area_to_copy.value);
  // select the tex
  text_area_to_copy.select();
  text_area_to_copy.focus();
}

function displayTextInResult(text_result) {
  // hide the element with the IMG and display the new view
  enableNoEmptyReultPanel();
  // display the value
  let result_text_area = document.getElementById("result-text-area");
  result_text_area.value = text_result;
}

// show the no message found view
function enableEmptyResultPanel() {
  document
    .querySelector(".result_section_card_images")
    .classList.remove("d-none");
  document
    .querySelector(".result_section_copy_section")
    .classList.add("d-none");
}

function enableNoEmptyReultPanel() {
  document.querySelector(".result_section_card_images").classList.add("d-none");
  document
    .querySelector(".result_section_copy_section")
    .classList.remove("d-none");
}

// this function decripts any text, the text using the inverse rules of encrypt
function decryptText() {
  // get the current value of the input
  let text = document
    .getElementById("input-text-area")
    .value.trim()
    .toLowerCase();
  // check if text is valid
  if (!text) {
    enableEmptyResultPanel();
    return;
  }
  // split the text
  let final_result = text
    .split(" ")
    .map((word) => getDecriptedWord(word))
    .join(" ");
  displayTextInResult(final_result);
}

// La letra "e" es convertida para "enter"
//     La letra "i" es convertida para "imes"
//     La letra "a" es convertida para "ai"
//     La letra "o" es convertida para "ober"
//     La letra "u" es convertida para "ufat"
function getDecriptedWord(current_word) {
  let result = current_word;

  // Iterate over each replacement and replace the value with the key
  replacements.forEach((value, key) => {
    const regex = new RegExp(value, "g"); // Create a global regex to replace all occurrences
    result = result.replace(regex, key);
  });
  return result;
}
