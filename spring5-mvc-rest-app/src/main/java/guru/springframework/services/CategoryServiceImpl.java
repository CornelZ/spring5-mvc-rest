package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
    this.categoryMapper = categoryMapper;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository
        .findAll() //
        .stream() //
        .map(categoryMapper::categoryToCategoryDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CategoryDTO getCategoryByName(String name) {
    final Category category = categoryRepository.findByName(name);
    return categoryMapper.categoryToCategoryDTO(category);
  }
}
