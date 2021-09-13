package br.com.antonina.toca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.antonina.toca.dto.CategoryDTO;
import br.com.antonina.toca.entities.Category;
import br.com.antonina.toca.repositories.CategoryRepository;
import br.com.antonina.toca.services.exceptions.EntityNotFoundExecption;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();

		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		/*
		 * List<CategoryDto> listDto = new ArrayList<>();
		 * 
		 * for (Category cat : list) { listDto.add(new CategoryDto(cat)); } return
		 * listDto;
		 */
	}

	/*
	 * Busca por Id as Categorias
	 */
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundExecption("Categoria não encontrada"));
		return new CategoryDTO(entity);
	}

	/*
	 * Inserção de nova Categoria
	 */
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);

	}
}
