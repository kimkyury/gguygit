import { defineCollection, z } from 'astro:content';

const culture = defineCollection({
  type: 'content',
  schema: z.object({
    title: z.string(),
    date: z.coerce.date(),
    cover: z.string().optional(),
    rating: z.number().min(0).max(5).optional(),
    youtube: z.string().url().optional(),
    summary: z.string().optional(),
    tags: z.array(z.string()).optional()
  })
});

const record = defineCollection({
  type: 'content',
  schema: z.object({
    title: z.string(),
    date: z.coerce.date(),
    cover: z.string().optional(),
    summary: z.string().optional(),
    tags: z.array(z.string()).optional()
  })
});

export const collections = { culture, record };
