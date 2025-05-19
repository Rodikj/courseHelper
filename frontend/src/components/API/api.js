const API_URL = "http://localhost:8080/api/courses";

export const createCourse = async (userId, courseName) => {
  const response = await fetch(`${API_URL}?userId=${userId}&courseName=${encodeURIComponent(courseName)}`, {
    method: "POST",
  });

  if (!response.ok) throw new Error("Failed to create course");
  return await response.json();
};

export const getCourses = async () => {
  const response = await fetch(API_URL);
  return await response.json();
};

export const getCourseById = async (id) => {
  const response = await fetch(`${API_URL}/${id}`);
  if (!response.ok) throw new Error("Course not found");
  return await response.json();
};