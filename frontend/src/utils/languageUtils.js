const languageMap = {
  es: 'Español',
  en: 'Inglés',
  fr: 'Francés',
  pt: 'Portugués',
  // Agrega otros idiomas según sea necesario
};

export const getLanguageName = (code) => {
  return languageMap[code] || code;
};
